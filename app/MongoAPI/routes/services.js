const express = require('express');
const router = express.Router();
const service = require('../models/services');

//Get all services
router.route('/')
.get(function(req, res) {
    service.find({}, function(err, response) {
        if(err) {
            return fail(res, err);
        } else {
            success(res, response)
        }
    });
})
//Create a service
.post(function(req, res, next) {
    service.create(req.body, function(err, response) {
        if(err) {
            if(err.name === 'MongoError' && err.code === 11000) {
                return fails(res, 'Service already exists in database');
            }
            return fails(res, err);
        } else {
            success(res, response);
        }
        res.end();
    });
});

router.route('/:serviceID')
.get(function(req, res) {
    service.findById(req.params.serviceID, function(err, response) {
        if(err) {
            fail (res, err);
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

module.exports = router;