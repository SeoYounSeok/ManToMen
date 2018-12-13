var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var reviewSchema = new Schema({
ReviewClassName: String,
ReviewuserID: String,
ReviewContents: String,
ReviewDate: String,
ReviewScore: String
})


module.exports=mongoose.model('review',reviewSchema);

