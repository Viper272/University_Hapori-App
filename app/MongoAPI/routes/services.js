const express = require('express');
const router = express.Router();
const service = require('../models/services');

//Get all services
router.route('/')
.get(function(req, res) {
    service.find({}, function(err, responce) {
        if(err) {
            return fail(res, err);
        } else {
            success(res, responce)
        }
    });
});

router.route('/:serviceID')
.get(function(req, res) {
    service.findById(req.params.serviceID, function(err, responce) {
        if(err) {
            fail (res, err);
        } else {
            success(res, responce);
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

module.exports = router;