var fs = require('fs');

exports.deletefile = function deletefile(path){
    var filename = "/home/ubuntu/MantoMen/public/images/"+path.substring(path.indexOf('com/')+4,path.length-2);
    fs.unlink(filename, function(err){
        if(err) console.log("aa");
        else console.log("파일이름: "+filename+" 삭제");
    });
}
