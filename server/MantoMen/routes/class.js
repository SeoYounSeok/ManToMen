var pictureURL;
var express = require('express');
var router = express.Router();
var Class = require('../models/class.js');
var Login = require('../models/login.js');
var Review = require('../models/review.js');
var https = require("https");
const jwt = require('jsonwebtoken')
var upload  = require('./multer').upload

router.post('/upload', upload.single("file"), function (req, res, next) {
    pictureURL = "http://ec2-54-180-106-61.ap-northeast-2.compute.amazonaws.com/"+req.file.originalname
    console.log(pictureURL);
    res.send('성공');
})

router.post('/push', function(req, res, next) {
    console.log(pictureURL);
    var newClass = new Class();
    newClass.ClassPicture = pictureURL;
    newClass.ClassName = req.body.ClassName;
    newClass.ClassTutorID = req.body.ClassTutorID;
//  newClass.ClassTuteeID = "null";
    newClass.ClassCategory = req.body.ClassCategory;
    newClass.ClassTotalPeople = req.body.ClassTotalPeople;
    newClass.ClassCurrentPeople = "0";
    newClass.ClassTutorIntro = req.body.ClassTutorIntro;
    newClass.ClassIntro = req.body.ClassIntro;
    newClass.ClassContents = req.body.ClassContents;
    newClass.ClassWhom = req.body.ClassWhom;
    newClass.ClassPrice = req.body.ClassPrice;
    newClass.ClassHour = req.body.ClassHour;
    newClass.ClassNumberOfTime = req.body.ClassNumberOfTime;
    newClass.ClassPlace = req.body.ClassPlace;
    newClass.ClassPlaceDetail = req.body.ClassPlaceDetail;
    newClass.ClassWeek = req.body.ClassWeek;
    newClass.ClassTime = req.body.ClassTime;
    newClass.ClassFirstTime = req.body.ClassFirstTime;
    newClass.ClassIdentity = "0";
    newClass.ClassScore = "0";

    console.log("\n\n post Class Name="+req.body.ClassName);
    newClass.save(function(err){
        if(err){
            console.error(err);
            res.send("0");
            return;
        }
    });

    Login.update({userID: req.body.ClassTutorID},
    {$set: {userPhoneNumber: req.body.userPhoneNumber}}, function(err,output){
    if(err) return res.send("error");
    if(output.nModified == 0){ res.send("0"); console.log("정보 수정 실패");}
    else { res.send("1"); console.log(req.body.ClassTutorID+"님의 번호 수정 완료"); }
    });


});

router.get('/review', function(req, res, next) {
    Review.find({ReviewClassName: req.query.value}).count(function(err, result){
       console.log(result);
        if(result != 0){
            Review.find({ReviewClassName: req.query.value}, function(err, result){
                if(err) return res.status(500).send({error: 'database failure'});
                if(result != null){ console.log(result); res.send(result); }
                else{ res.send("0"); console.log("0") }
            });
        }
        else res.send("2");
    });
});

router.post('/reviewinsert', function(req, res, next) {
    Class.find({ClassTuteeID: {'$regex': req.body.ReviewuserID}}).count(function(err, result){
    console.log(result);
    if (result == '0'){
    console.log("수강자가 아닙니다.");
    } else{
    console.log(result);
    console.log("수강자 입니다.");
    }
    });

    var score = 0;
    var newReview = new Review();
    newReview.ReviewClassName = req.body.ReviewClassName;
    newReview.ReviewuserID = req.body.ReviewuserID;
    newReview.ReviewContents = req.body.ReviewContents;
    newReview.ReviewDate = req.body.ReviewDate;
    newReview.ReviewScore = req.body.ReviewScore;
    console.log("\n\n post Review="+req.body.ReviewuserID);
    newReview.save(function(err, result){
        if(err){
            console.error(err);
            res.send(err);
            return;
        }
        if(result != null) {
            Review.find({ReviewClassName: req.body.ReviewClassName},{_id: 0, ReviewScore: 1}, function(err, output){
            Review.find({ReviewClassName: req.body.ReviewClassName}).count(function(err,result){
            var json = JSON.stringify(output);
            var jsonarr = JSON.parse(json);
            for ( var i in jsonarr ) {
            score = score + parseInt(jsonarr[i].ReviewScore) ;
            }
            var n = result;
console.log("이건 n값"+n);
            var average = score/n ;
            Class.update({ClassName: req.body.ReviewClassName},
            {$set: {ClassScore: average.toFixed(1) }}, function(err,output){
            if(err) return res.send("error");
            else {res.send("1");}
            });
            });
            });
        }
        else{ res.send("0")};
    });
});
/*
Class.update({ClassName: req.body.ClassName,
    ClassTuteeID: {'$regx': req.body.ClassTuteeID}},
    {$set: {$push: {ClassReview:
     {userID: req.body.userID, Contents: req.body.Contents,
    Date: req.body.Date, Score: 'aaa' }}}} ,function(err, result){
        if(err) return res.status(500).send({error: 'database failure'});
        if(result != null){res.send('리뷰추가 성공');}
    });
});
*/
router.get('/get', function(req, res, next) {
    Class.count(function(err, result){
    if(result == 0) { res.send("2"); }
    else{
        Class.find({ClassIdentity: "1"}).sort({ClassScore: -1}).exec(function (err, result){
            console.log(result);
            if(err) return res.status(500).send({error: 'database failure'});
            if(result != null){ console.log(result); res.send(result); }
            else{ res.send("0"); }
            });
        }
    });
});

router.post('/detail', function(req, res, next) {
    console.log(req.body.ClassName);
    Class.find({ClassName: req.body.ClassName}, function(err, result){
        if(err) return res.status(500).send({error: 'database failure'});
        if(result != null){ res.send(result); }
        else{ res.send("0"); }
    });
});


/* GET users listing. */
router.post('/sms', function(req, res, next) {
    var rand = Math.floor((Math.random() * (9999-1000+1)))+1000;
    var credential = 'Basic '+new Buffer('diqmwl:73b089b6e2f611e88fd30cc47a1fcfae').toString('base64'); 
    var receiver = req.body.userPhoneNumber;
    var data = { 
    "sender"     : "01095192750", 
    "receivers"  : [receiver], 
    "content"    : "인증번호를 확인후 입력해주세요 :" + rand 
    } 
    var body = JSON.stringify(data); 
    var options = { 
    host: 'api.bluehouselab.com', 
    port: 443, 
    path: '/smscenter/v1.0/sendsms', 
    headers: { 
        'Authorization': credential, 
        'Content-Type': 'application/json; charset=utf-8', 
        'Content-Length': Buffer.byteLength(body) 
    }, 
    method: 'POST' 
    }; 
    var req = https.request(options, function(res) { 
    console.log(res.statusCode); 
    var body = ""; 
    res.on('data', function(d) { 
        body += d; 
    }); 
    res.on('end', function(d) { 
        if(res.statusCode==200) 
  	    console.log(JSON.parse(body)); 
        else 
  	    console.log(body); 
    }); 
    }); 
    req.write(body); 
    req.end(); 
    req.on('error', function(e) { 
        console.error(e); 
    });
    res.send({"rand": rand});
    
});

router.get('/tutormanage', function(req, res, next) {
    Class.find({ClassIdentity: 0}, function(err, result){
        if(err) return res.status(500).send({error: 'database failure'});
        if(result != null){ console.log(result); res.send(result); }
        else{ res.send("0"); }
    });
});

router.post('/accept', function(req, res, next){
console.log(req.body.ClassName);
    Class.update({ClassName: req.body.ClassName},
    {$set: {ClassIdentity: "1"}}, function(err,output){
    if(err) return res.send("error");
    if(output.nModified == 0){ res.send("0"); console.log("정보 수정 실패");}
    else { 
        Login.update({userID: req.body.ClassTutorID},
        {$set: {userIdentity: "Tutor", userOperateClass: req.body.ClassName}}, function(err,output){
        if(err) return res.send("error");
        if(output.nModified == 0){ res.send("0"); console.log("정보 수정 실패");}
        else { res.send("1"); console.log("튜터 등록 및 클래스생성 완료"); }
        });
    }
    });
});

router.post('/deny', function(req, res, next) {
console.log(req.body.ClassName);
    Class.remove({ClassName: req.body.ClassName}, function(err, output){
    if(err) return res.send("error");
    if(output.n == 0){ res.send("0"); console.log("삭제 실패");}
    else { res.send("1"); console.log("클래스 미승인 성공"); }
    });
});

router.post('/classmanage', function(req, res, next) {
    Class.find({ClassTutorID: req.body.ClassTutorID}, function(err, result){
        if(err) return res.status(500).send({error: 'database failure'});
        if(result != null){ res.send(result); }
        else{ res.send("0"); }
    });
});

router.post('/apply', function(req, res, next) {
        Login.find({userID: req.body.userID},{_id: 0, userPoint: 1}, function(err, result){
        userPoint = parseInt(result[0].userPoint);

        if(err){return res.send("에러발생");}

        else{
            Class.find({ClassName: req.body.ClassName},{_id: 0, ClassTotalPeople: 1, ClassTuteeID: 1, ClassPrice: 1, ClassTutorID: 1},function(err,result){
            Totalpeople = parseInt(result[0].ClassTotalPeople);
            Currentpeople = parseInt(result[0].ClassTuteeID.length);
            ClassPrice = parseInt(result[0].ClassPrice);
            ClassTutorID = result[0].ClassTutorID;
            console.log(userPoint+""+ClassPrice);

            if(userPoint < ClassPrice){ return res.send("포인트가 부족합니다"); }

            if(Totalpeople <= Currentpeople) {console.log("정원초과라구욧"); return res.send("정원초과 입니다.");}
            else{
                Class.find({ClassName: req.body.ClassName, ClassTutorID: req.body.userID}).count(function(err, result){
                if(err) return res.status(500).send({error: 'database failure'});
                if(result != "0"){ res.send("튜터는 신청할 수 없습니다."); }
                else{
                    Class.find({ClassName: req.body.ClassName, ClassTuteeID: req.body.userID}).count( function(err, result){
                    if(err) return res.status(500).send({error: 'database failure'});
                    if(result != "0"){ res.send("이미 등록된 수업 입니다"); }
                    else{
                        Class.update({ClassName: req.body.ClassName},
                        {$addToSet: {ClassTuteeID: req.body.userID}}, function(err,output){
                        if(err) return res.send("error");
                        else {
                            Class.update({ClassName: req.body.ClassName},
                            {$set: {ClassCurrentPeople: Currentpeople+1}},function(err,result){
			    if(err) console.log(err);
			    else{
                                Login.update({userID: req.body.userID},
                                {$addToSet: {userParticipateClass: req.body.ClassName}}, function(err, result) {
                                if(err){return res.send("에러발생!!");}
                                else{
                                    Login.update({userID: req.body.userID},
                                    {$set: {userPoint: userPoint-ClassPrice}},function(err, result){
                                    if(err){return res.send("에러발생여기");}
                                    else{
                                    Login.find({userID: ClassTutorID},{_id: 0, userPoint: 1}, function(err, result){
                                    TutorPoint = parseInt(result[0].userPoint);
                                    if(err) { return res.send("포인트조회 에러!");}
                                    else{
                                        Login.update({userID: ClassTutorID},
                                        {$set: {userPoint: TutorPoint+ClassPrice}},function(err, result){
                                        if(err){return res.send("포인트 추가 에러!");}
                                        else{return res.send("1");} 
                                        });
                                    }
                                    });
                                    

                                    }
                                    });
                                }
                                });
			    }
			    });
                        }
                        });

                    }
                    });
                }
                });
    
            }
    
//클래스

            });
        }
        });
});

router.get('/search', function(req, res, next) {
    Class.find({ClassName: {'$regex': req.query.value}, ClassIdentity: 1}).sort({ClassScore: -1}).exec(function (err, result){
        if(err) return res.status(500).send({error: 'database failure'});
        if(result != null){ console.log(result); res.send(result); }
        else{ res.send("0"); }
        });
});

router.get('/categorysearch', function(req, res, next) {
    Class.find({ClassCategory: req.query.value, ClassIdentity: 1}).sort({ClassScore: -1}).exec(function (err, result){
        if(err) return res.status(500).send({error: 'database failure'});
        if(result != null){ console.log(result); res.send(result); }
        else{ res.send("0"); }
        });
});

router.post('/delete', function(req, res, next){
console.log(req.body.ClassName);
    Class.remove({ClassName: req.body.ClassName}, function(err,output){
    if(err) return res.send("error");
    if(output.n == 0){ res.send("0"); console.log("삭제할것이 없습니다");}
    else { 
        Login.update({userID: req.body.ClassTutorID},
        {$set: {userIdentity: "Tutee", userOperateClass: req.body.ClassName}}, function(err,output){
        if(err) return res.send("error");
        if(output.nModified == 0){ res.send("0"); console.log("정보 수정 실패");}
        else { res.send("1"); console.log("튜터 등록 및 클래스생성 완료"); }
        });
    }
    });
});


module.exports = router;
