AUI().ready('liferay-sign-in-modal',
    function (A) {
        // Insert snippet 01-variables-and-sign-in here

        var BODY = A.getBody();

        var signIn = A.one('#sign-in');

        if (signIn && signIn.getData('redirect') !== 'true') {
            signIn.plug(Liferay.SignInModal);
        }

        var fullScreenToggleIcon = A.one('.full-screen-navigation #banner .navbar-toggler');
        // Insert snippet 02-close-nav-click here

        if (fullScreenToggleIcon) {
            fullScreenToggleIcon.on(
                'click',
                function (event) {
                    BODY.toggleClass('main-nav-opened', event.currentTarget.hasClass('collapsed'));
                }
            );
        }
    }
);

function parseStringToDate(strDate, hours, minutes, seconds, miliSeconds) {
    const REGEX_SP_DATE = /[.,\/ -]/;
    hours = !isNaN(hours) ? hours : 0;
    minutes = !isNaN(minutes) ? minutes : 0;
    seconds = !isNaN(seconds) ? seconds : 0;
    miliSeconds = !isNaN(miliSeconds) ? miliSeconds : 0;

    if (strDate && strDate.split(REGEX_SP_DATE).length === 3) {
        const tkDate = strDate.split(REGEX_SP_DATE);
        let date;

        if (tkDate[0].length > 2) {
            date = new Date(tkDate[0], tkDate[1] - 1, tkDate[2], hours, minutes, seconds, miliSeconds);

            return date && compareDate(tkDate[2], tkDate[1], tkDate[0], date) ? date: undefined;
       } else {
            date = new Date(tkDate[2], tkDate[1] - 1, tkDate[0], hours, minutes, seconds, miliSeconds);

            return date && compareDate(tkDate[0], tkDate[1], tkDate[2], date) ? date: undefined;
        }
    } else {
        return undefined;
    }
}


function compareDate(day, month, year, date){

     return date.getDate() === +day && (date.getMonth() +1) === +month && date.getFullYear() === +year;
}

var validator = validator || {};

 validator.isValidDate = function (value){
     let result = parseStringToDate(value, 0,0,0,0);
     return !!result;
 }

 //Custom validations

AUI().use('aui-form-validator', function (A) {
    var defaultFormValidator = A.config.FormValidator;

    A.mix(
        defaultFormValidator.RULES,
        {
            gmail: function (value, fieldNode, ruleValue) {
                return value.endsWith('@liferay.com');
            },
            validDate:function  (value, fieldNode, ruleValue){
                return validator.isValidDate(value);
            }
        },
        true
    );

    A.mix(
        defaultFormValidator.STRINGS,
        {
            gmail: Liferay.Language.get('please-enter-a-valid-gmail-address'),
            validDate: Liferay.Language.get('please-enter-a-valid-date')
        },
        true
    );
});