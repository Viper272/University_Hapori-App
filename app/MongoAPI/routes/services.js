const express = require('express');
const router = express.Router();
const service = require('../models/services.js');

router.use(express.json());

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
            if(err.name === 'MongoServerError' && err.code === 11000) {
                return fail(res, 'Service already exists');
            }
            return fail(res, err);
        } else {
            success(res, response);
        }
        res.end();
    })
});
// .delete(function(req, res) {
//     service.deleteMany({}, function(err, reponse) {
//         if(err) {
//             return fail(res, err);
//         } else {
//             success(res, response);
//         }
//     })
// });

router.route('/:serviceID')
.get(function(req, res) {
    service.findById(req.params.serviceID, function(err, response) {
        if(err) {
            return fail(res, err);
        } else {
            success(res, response);
        }
    });
})
.delete(function(req, res) {
    service.deleteOne(req.params.serviceID, function(err, response) {
        if(err) {
            return fail(res, err);
        } else {
            success(res, response);
        }
    })
});

router.route('/category/:categoryID')
.get(function(req, res) {
    service.find({tags: req.params.categoryID}, function(err, response) {
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
