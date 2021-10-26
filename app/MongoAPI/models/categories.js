const mongoose = require('mongoose');
const Schema = mongoose.Schema;

var CategorySchema = new Schema({
    name: {
        type:String,
        required:true,
        unique:true
    }
});

module.exports = mongoose.model("Categories", CategorySchema);
