var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var loginSchema = new Schema({
userPicture: String,
userID:{
type:String,
unique:true
},
userPassword: String,
userEmail:{
type:String,
unique:true
},
userName: String,
userAge: String,
userGender: String,
userCategory: String,
userIdentity: String,
userParticipateClass: [String],
userOperateClass: String,
userPhoneNumber:{
type:String,
unique:true
},
userPoint: String
});

module.exports=mongoose.model('login',loginSchema);
