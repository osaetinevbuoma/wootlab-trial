'use strict';

const phone_input = document.querySelector('#billing_phone');
const iti = window.intlTelInput(phone_input, {
    initialCountry: 'NG',
    geoIpLookup: function(callback) {
        $.get('https://ipinfo.io', function() {}, 'jsonp').always(function(resp) {
            let countryCode = (resp && resp.country) ? resp.country : '';
            console.log(resp);
            console.log(countryCode);
            callback(countryCode);
        });
    },
    utilsScript: '/javascripts/lib/intl-tel-input/utils.js'
});