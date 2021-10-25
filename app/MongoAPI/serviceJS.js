var express = require('express');
var bodyParser = require('body-parser');
var service = require('./catagory-model.js');

var serviceRouter = express.Router();
serviceRouter.use(bodyParser.json());

serviceRouter.route('/')
.get(function(req, res) {
    post.find({}, function(err, response) {
        if(err) {
            return fail(res, err);
        } else {
            success(res, response);   
        }
    });
});

function success(res, data) {
    if(data == null || (data instanceof Array && (data[0] == null || data[0] == 'undefined'))) {
        fail(res, "No Data Avaliable")
    } else {
        res.json({
            success: true,
            message:"",
            data: data
        });
    }
} 

function fail(res, err) {
    return res.status(500).send({success:false, message:err});
}

modules.export = serviceRouter;