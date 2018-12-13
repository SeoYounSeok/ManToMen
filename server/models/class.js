var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var classSchema = new Schema({
ClassPicture: String, //사진
ClassName: String,  //이름
ClassTutorID: String,  //튜더아이디
ClassTuteeID: [String],  //튜티아이디
ClassCategory: String,  //카테고리
ClassTotalPeople: String,  //총모집인원
ClassCurrentPeople: String,  //현재인원
ClassTutorIntro: String, //클래스 튜터 소개
ClassIntro: String,  //클래스 소개
ClassContents: String,  //클래스 수업 내용
ClassWhom: String,  //수업대상자
ClassPrice: String, //수업료
ClassHour: String, //1회에 몇시간 수업하는지
ClassNumberOfTime: String, //총 몇번 진행하는지
ClassPlace: String, //수업장소
ClassPlaceDetail: String, //수업상세장소
ClassWeek: String, //수업 요일
ClassTime: String, //몇시부터 몇시인지
ClassFirstTime: String, //첫 수업일
ClassIdentity: String, //이거 승인 되었는지 판별
ClassScore: String
});

module.exports=mongoose.model('class',classSchema);
