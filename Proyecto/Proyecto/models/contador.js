const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const contadorSchema = new Schema({
    id: { type: String, required: true },
    seq: { type: Number, default: 0 }
});

module.exports = mongoose.model('Contador', contadorSchema);