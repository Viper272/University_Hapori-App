var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var ContactDetails = new Schema({
    phone: {type:String, required:true},
    email: {type:String, required:true}
});

var ServiceModel = new Schema({
    name: {
        type:String,
        require:true,
        unique:true
    },
    shortDescription: {
        type:String,
        
    },
    longDescription: {
        type:String,
    },
    contact: [ContactDetails]
});


var Service = mongoose.model("services", ServiceModel);
module.exports = Service;