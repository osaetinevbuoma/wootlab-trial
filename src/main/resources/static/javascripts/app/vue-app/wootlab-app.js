'use strict';

const csrf_header = $('meta[name="_csrf_header"]').attr('content'),
    csrf_token = $('meta[name="_csrf"]').attr('content');
let headers = {};
headers[csrf_header] = csrf_token;

const wootlab_app = new Vue({
    el: '#wootlab-app',
    data: {
        cart_count: 0,
        cart_items: [],
        quantity: 1,
        sub_total: 0,
        show: false
    },
    mounted: function () {
        this.getCart();
    },
    methods: {
        /**
         * Get customer's product stored in cart.
         */
        getCart: function () {
            axios.get('/cart/list', { headers: headers }).then((response) => {
                this.cart_items = response.data;
                this.cart_count = this.cart_items.length;
            }).catch((error) => {
                console.log(error);
            });
        },
        /**
         * Add product to customer's cart.
         * @param productId id of product to be added to cart.
         */
        addToCart: function (productId) {
            let data = {
                product_id: productId,
                quantity: this.quantity
            };

            axios.post('/cart/add', data, { headers: headers })
                .then((response) => {
                    if (response.data) {
                        this.cart_count += 1;
                        this.show = true;
                        setTimeout(() => {
                            this.show = false;
                        }, 1500);
                    }
                }).catch((error) => {
                console.log(error);
            });
        },
        /**
         * Remove an item from cart.
         * @param index         the index of the product to remove from cart array
         * @param delete_all    boolean determining whether to remove all items from cart
         */
        removeFromCart: function (index, delete_all) {
            if (delete_all) {
                axios.delete('/cart/delete/all', { headers: headers }).then((response) => {
                    this.cart_items = [];
                    this.cart_count = this.cart_items.length;
                }).catch((response) => {
                    console.log(response);
                });
            } else {
                let product = this.cart_items[index];
                axios.delete('/cart/delete/' + product.id, { headers: headers }).then((response) => {
                    this.cart_items.splice(index, 1);
                    this.cart_count = this.cart_items.length;
                }).catch((response) => {
                    console.log(response);
                });
            }
        },
        /**
         * Compute sub total price of items in cart
         * @param price         price of product
         * @param quantity      quantity of product item
         * @returns {string}    formatted sub total price
         */
        computeSubTotal: function (price, quantity) {
            return this.formatPrice(price * quantity);
        },
        /**
         * Compute grand total cost of items in cart.
         * @returns {string}    formatted total of grand total cost
         */
        computeTotal: function () {
            let total = 0;
            for (let i = 0; i < this.cart_items.length; i++) {
                total += (this.cart_items[i].price * this.cart_items[i].quantity);
            }

            return this.formatPrice(total);
        },
        /**
         * Format prices for better display in view
         * @param price         price of product to format
         * @returns {string}    formatted price
         */
        formatPrice: function (price) {
            return '₦' + price.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g,
                '$1,');
        },
        /**
         * Update quantity of products in customer's cart.
         */
        updateCart: function () {
            let data = [];
            for (let i = 0; i < this.cart_items.length; i++) {
                let product_info = {
                    cart_id: this.cart_items[i].id,
                    quantity: this.cart_items[i].quantity
                };
                data.push(product_info);
            }

            axios.put('/cart/update', { data: data }, { headers: headers }).then((response) => {
                this.show = true;
                setTimeout(() => {
                    this.show = false;
                }, 1500);
            }).catch((error) => {
                console.log(error);
            });
        }
    }
});