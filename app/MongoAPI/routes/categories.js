const express = require('express');
const router = express.Router();
const category = require('../models/categories.js');

router.use(express.json());

//Get all services
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
//Create a category
.post(function(req, res, next) {
    category.create(req.body, function(err, response) {
        if(err) {
            console.log(err.name);
            if(err.name === 'MongoServerError' && err.code === 11000) {
                return fail(res, 'Category already exists');
            }
            return fail(res, err);
        } else {
            success(res, response);
        }
        res.end();
    })
});
//.delete(function(req, res) {
//     category.deleteMany({}, function(err, reponse) {
//         if(err) {
//             return fail(res, err);
//         } else {
//             success(res, response);
//         }
//     })
// });

router.route('/:categoryID')
.delete(function(req, res) {
    category.deleteOne(req.params.categoryID, function(err, response) {
        if(err) {
            return fail(res, err);
        } else {
            success(res, response);
        }
    })
});

function success(res, data) {
    if(data == null || (data instanceof Array && (data[0] == null || data[0] == 'undefined'))) {
        return fail(res, "No Data Avaliable")
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
