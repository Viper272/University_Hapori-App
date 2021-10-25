const express = require('express');
const router = express.Router();
const category = require('../models/categories');

//Get all Categories
router.route('/')
.get(function(req, res) {
    category.find({}, function(err, response) {
        if(err) {
            return fail(res, err);
        } else {
            success(res, response)
        }
    });
})
//Create a Category
.post(function(req, res) {
    category.create(req.body, function(err, response) {
        if(err) {
            if(err.name === 'MongoError' && err.code === 11000) {
                return fail(res, 'Category already exists in database');
            }
            return fail(res, err);
        } else {
            success(res, response);
        }
        res.end();
    });
});

router.route('/:categoryID')
.delete(function(req, res) {
    category.deleteOne(req.params.categoryID, function(err, response) {
        if(err) {
            fail(res, err);
        } else {
            success(res, response);
        }
    })
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