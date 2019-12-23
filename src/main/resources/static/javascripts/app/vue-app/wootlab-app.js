'use strict';

const csrf_header = $('meta[name="_csrf_header"]').attr('content'),
    csrf_token = $('meta[name="_csrf"]').attr('content');
let headers = {};
headers[csrf_header] = csrf_token;

const wootlab_app = new Vue({
    el: '#wootlab-app',
    data: {
        cart_count: 0,
        cart_items: []
    },
    mounted: function () {
        this.getCart();
    },
    methods: {
        getCart: function () {
            axios.get('/cart/list', { headers: headers }).then((response) => {
                this.cart_items = response.data;
                this.cart_count = this.cart_items.length;
            }).catch((error) => {
                console.log(error);
            });
        },
        addToCart: function (productId) {
            axios.post('/cart/add', { product_id: productId }, { headers: headers })
                .then((response) => {
                    if (response.data) {
                        this.cart_count += 1;
                    }
                }).catch((error) => {
                console.log(error);
            });
        }
    }
});