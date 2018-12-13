const jwt = require('jsonwebtoken')
var config = require('./routes/config')
const authMiddleware = (req, res, next) => {
    // read the token from header or url 
    var head = req.headers
    var json = JSON.stringify(head)
    var jsonarr = JSON.parse(json)
    var cookie = jsonarr.cookie
    var token = cookie.substring(cookie.indexOf("=")+1);
    console.log("토큰이다"+token+"시크릿이다"+config.secret);

 if(!token) {
        return res.send("로그인 정보가 없습니다.");
    }
    // create a promise that decodes the token
        const p = new Promise(
        (resolve, reject) => {
            jwt.verify(token, config.secret, (err, decoded) => {
                if(err) return res.send("토큰 만료") 
		if(decoded) next();
                else return res.send("토큰값 오류")
            })
        }
    )

}

module.exports = authMiddleware
