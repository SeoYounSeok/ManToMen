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
var pictureURL;
var upload  = require('./multer').upload

router.post('/upload', upload.single("file"), function (req, res, next) {
    pictureURL = "http://ec2-54-180-106-61.ap-northeast-2.compute.amazonaws.com/"+req.file.originalname
    console.log(pictureURL);
    res.send('성공');
})

/* GET home page. */
router.post('/register', function(req, res, next) {
    var Password = crypto.createHash("sha512").update(req.body.userPassword).digest("hex")+c.salt;
    var newLogin = new Login();
    newLogin.userPicture = pictureURL;
    newLogin.userID = req.body.userID;
    newLogin.userPassword = Password;
    newLogin.userEmail = req.body.userEmail;
    newLogin.userName = req.body.userName;
    newLogin.userAge = req.body.userAge;
    newLogin.userGender = req.body.userGender;
    newLogin.userCategory = req.body.userCategory;
    newLogin.userIdentity = "Tutee";
//    newLogin.userParticipateClass = "null";
    newLogin.userOperateClass = "null";
    newLogin.userPhoneNumber = req.body.userID;
    newLogin.userPoint = "0";
    console.log("\n\n post req.body.id="+req.body.userCategory);
    newLogin.save(function(err){
        if(err){
            console.error(err);
            res.send("회원가입에 실패하였습니다");
            return;
        }
        res.send("1");
    });
});

router.post('/login', function(req, res, next) {
    var Password = crypto.createHash("sha512").update(req.body.userPassword).digest("hex")+c.salt;
    console.log("\n\n post req.body.title="+req.body.userID);
    Login.findOne({userID: req.body.userID, userPassword: Password}, function(err, result){
        if(err) return res.status(500).send({error: 'database failure'});
        if(result != null){
	    let token = jwt.sign({
            id: req.body.userID,
	    },
	    config.secret,
	    {
	    expiresIn: 1800,
	    }
        );
	res.cookie("user",token);
	res.send(result);
console.log(result);	
//res.json({
	//    token: token
	//});
	}
        else{res.send("0");}
    })
});

router.post('/auth', function(req, res, next) {
    var userEmail = req.body.userEmail;
    var rand = Math.floor((Math.random() * (9999-1000+1)))+1000;
    console.log(userEmail);
    Login.count({userEmail: userEmail}, function(err, result){
        if(err) return res.status(500).send({error: 'database failure'});
        if(result != 0){console.log(result); res.send("0");}
        else{ 

            console.log(rand+":"+req.body.userEmail);

            var transporter = nodemailer.createTransport({
                service: 'gmail',
                auth: {
                    user: 'mantomen7777@gmail.com',
                    pass: '1q2w3e4r!@'
                }
            });

            var mailOptions = {
                from: 'mantomen7777@gmail.com',
                to: userEmail,
                subject: 'ManToMen 회원가입 인증메일입니다.',
                text: '다음 인증번호를 입력하세요 : '+rand
            };

            transporter.sendMail(mailOptions, function(error, info){
                if (error) {
                console.log(error+"케ㅔ케케");
                } else {
                console.log('Email sent: ' + info.response);
                }
            });

            res.send({"rand":rand});
        }
    });
});
router.post('/auth', function(req, res, next) {
    var userEmail = req.body.userEmail;
    var rand = Math.floor((Math.random() * (9999-1000+1)))+1000;
    console.log(userEmail);
    Login.count({userEmail: userEmail}, function(err, result){
        if(err) return res.status(500).send({error: 'database failure'});
        if(result != 0){console.log(result); res.send("0");}
        else{ 

            console.log(rand+":"+req.body.userEmail);

            var transporter = nodemailer.createTransport({
                service: 'gmail',
                auth: {
                    user: 'mantomen7777@gmail.com',
                    pass: '1q2w3e4r!@'
                }
            });

            var mailOptions = {
                from: 'mantomen7777@gmail.com',
                to: userEmail,
                subject: 'ManToMen 회원가입 인증메일입니다.',
                text: '다음 인증번호를 입력하세요 : '+rand
            };

            transporter.sendMail(mailOptions, function(error, info){
                if (error) {
                console.log(error+"케ㅔ케케");
                } else {
                console.log('Email sent: ' + info.response);
                }
            });

            res.send({"rand":rand});
        }
    });
});


module.exports = router;
