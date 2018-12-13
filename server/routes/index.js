var pictureURL = "null";
var cookieParser = require('cookie-parser');
var express = require('express');
var router = express.Router();
var Login = require('../models/login.js');
var nodemailer = require('nodemailer');
var crypto = require('crypto');
var c = require('./salt');
var jwt = require('jsonwebtoken');
var config = require('./config.js');
var tokencheck = require('../tokenhelp');
var upload  = require('./multer').upload
var filemanage = require('./filemanage');
var Class = require('../models/class.js');

router.post('/upload', upload.single("file"), function (req, res, next) {
    pictureURL = "http://ec2-54-180-106-61.ap-northeast-2.compute.amazonaws.com/"+req.file.originalname
    console.log(pictureURL);
    res.send('성공');
})

router.post('/modify', function(req, res, next) {
    var Password = crypto.createHash("sha512").update(req.body.userPassword).digest("hex")+c.salt;
    if(pictureURL == "null"){
        Login.update({userID: req.body.userID},
        {$set: {userPassword: Password, userName: req.body.userName, userAge: req.body.userAge, userCategory: req.body.userCategory}}, function(err,output){
        if(err) return res.send("error");
        if(output.nModified == 0){ res.send("0"); console.log("정보 수정 실패");}
        else { res.send("1"); console.log(req.body.userID+"님의 정보 수정 완료"+output); }
        });
    }
    else{
        Login.findOne({userID: req.body.userID},{_id: 0, userPicture: 1}, function(err, result){
        filemanage.deletefile(JSON.stringify(result));
        })
        Login.update({userID: req.body.userID},
        {$set: {userPicture: pictureURL ,userPassword: Password, userName: req.body.userName, userAge: req.body.userAge, userCategory: req.body.userCategory}}, function(err,output){
        if(err) return res.send("error");
        if(output.nModified == 0){ res.send("0"); console.log("정보 수정 실패");}
        else { res.send("1"); console.log(req.body.userID+"님의 정보 수정 완료"+output); }
        });
    }
});

router.post('/delete', function(req, res, nex) {
    console.log("\n\n delete ID="+req.body.userID);
    Login.remove({userID: req.body.userID}, function(err, output){
    if(err) return res.send("error");
    if(output.n == 0){ res.send("0"); console.log("삭제 실패");}
    else { res.send("1"); console.log(req.body.userID+"삭제 성공"+output); }
    });
});

router.post('/login/get', function(req, res, next) {
    console.log("\n\n post req.body.title="+req.body.userID);
    Login.findOne({userID: req.body.userID}, function(err, result){
        if(err) return res.status(500).send({error: 'database failure'});
        if(result != null){res.send(result);console.log(req.body.userID);}
        else{res.send("0");}
    })
});



router.get('/admin', function(req, res, next) {
    Login.find(function(err, result){
    console.log(result);
    if(err) return res.status(500).send({error: 'database failure'});
    if(result != null){ res.send(result); }
    else{ res.send("0"); }
    });
});

router.get('/participation', function(req, res, next) {
    Login.find({userID: req.query.value},{_id: 0, userParticipateClass: 1}).count(function(err, output){
    if(output == 0) { res.send("0"); console.log("개수가 0개라 에러")}
    else {
        Login.find({userID: req.query.value},{_id: 0, userParticipateClass: 1} ,function(err, resultarr){
	for(var i = 0; i<resultarr.length; i++){
	Class.find({ClassName: resultarr[i].userParticipateClass}, function(err, result){
        res.send(result);
	});
	}
        });
    }
    });
});

router.post('/point', function(req, res, next) {
    Login.find({userID: req.body.userID}, {_id:0, userPoint: 1}, function(err, result){
    var point = parseInt(result[0].userPoint) + parseInt(req.body.userPoint);
    console.log(point);
    Login.update({userID: req.body.userID},
    {$set: {userPoint: point}}, function(err,output){
    if(err) return res.send("error");
    if(output.nModified == 0){ res.send("0"); console.log("정보 수정 실패");}
    else { res.send("1"); console.log(req.body.userID+"님의 포인트 추가 완료"); }
    });
    });
});

router.post('/changeidentity', function(req, res, next) {
    if(req.body.userIdentity == "Tutee"){identity = "Tutor";}
    else{identity = "Tutee";}
    console.log(identity);
    Login.update({userID: req.body.userID},
    {$set: {userIdentity: identity}}, function(err,output){
    if(err) return res.send("error");
    if(output.nModified == 0){ res.send("0"); console.log("정보 수정 실패");}
    else { res.send("1"); console.log(req.body.userID+"님의 수정 완료");}
    });
});

router.post('/changepoint', function(req, res, next) {
    Login.update({userID: req.body.userID},
    {$set: {userPoint: req.body.userPoint}}, function(err,output){
    if(err) return res.send("error");
    if(output.nModified == 0){ res.send("0"); console.log("정보 수정 실패");}
    else { res.send("1"); console.log(req.body.userID+"님의 수정 완료");}
    });
});


router.post('/aa', function(req, res, next) {
  var head = req.headers;
              var json = JSON.stringify(head);
            var jsonarr = JSON.parse(json);
  console.log("할롱"+jsonarr.user);
});

module.exports = router;
