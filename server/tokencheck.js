var secretObj = require("./routes/config");
var jwt = require('jsonwebtoken')
var cookieParser = require('cookie-parser');
var request = function (req, res, next){

    var head = req.headers
    var json = JSON.stringify(head)
    var jsonarr = JSON.parse(json)
    var token = jsonarr.user
    console.log(token+""+config.secret);

  if(jwt.verify(token, secretObj.secret)){
    res.send("권한이 있어서 API 수행 가능")
  }
  else{
	console.log('asa');
    res.send("권한이 없습니다.")
  }
};

module.exports = request
