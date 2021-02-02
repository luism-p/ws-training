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

AUI().use('aui-form-validator', function (A) {
    var defaultFormValidator = A.config.FormValidator;

    A.mix(
        defaultFormValidator.RULES,
        {
            gmail: function (value, fieldNode, ruleValue) {
                if (!value)
                    return true;

                return value.endsWith('@liferay.com');
            },
            dateCustom:function  (value, fieldNode, ruleValue){

                return parseStringToDate(value, 0,0,0,0) !== undefined;
            }
        },
        true
    );

    A.mix(
        defaultFormValidator.STRINGS,
        {
            gmail: Liferay.Language.get('please-enter-a-valid-gmail-address'),
            dateCustom: Liferay.Language.get('please-enter-a-valid-date')
        },
        true
    );
});

 const parseStringToDate = function (strDate, hours, minutes, seconds, miliSeconds) {
     console.log(strDate);
    const REGEX_SP_DATE = /[.,\/ -]/;
    hours = !isNaN(hours) ? hours : 0;
    minutes = !isNaN(minutes) ? minutes : 0;
    seconds = !isNaN(seconds) ? seconds : 0;
    miliSeconds = !isNaN(miliSeconds) ? miliSeconds : 0;

    if (strDate && REGEX_SP_DATE.exec(strDate)) {
        var tkDate = strDate.split(REGEX_SP_DATE);

        if (tkDate[0].length > 2) {
            var date = new Date(tkDate[0], tkDate[1] - 1, tkDate[2], hours, minutes, seconds, miliSeconds);

            return compareDate(tkDate[2], tkDate[1], tkDate[0], date) ? date: undefined;
       } else {
            var date = new Date(tkDate[2], tkDate[1] - 1, tkDate[0], hours, minutes, seconds, miliSeconds);

            return compareDate(tkDate[0], tkDate[1], tkDate[2], date) ? date: undefined;
        }
    } else {
        return undefined;
    }
}


const compareDate = function (day, month, year, date){
     return date.getDay() === +day && (date.getMonth() +1) === +month && date.getFullYear() === +year;
}
