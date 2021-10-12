const mongoose = require('mongoose');
const Schema = mongoose.Schema;

var ContactDetails = new Schema({
    phone: {type:String, required:true},
    email: {type:String, required:true}
});

var ServiceSchema = new Schema({
    name: {
        type:String,
        required:true,
        unique:true
    },
    shortDescription: {
        type:String,
        required:true  
    },
    longDescription: {
        type:String,
    },
    contact: [ContactDetails],
    tags: [String]
});

module.exports = mongoose.model("Services", ServiceSchema);