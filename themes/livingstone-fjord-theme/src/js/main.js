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
        },
        true
    );

    A.mix(
        defaultFormValidator.STRINGS,
        {
            gmail: Liferay.Language.get('please-enter-a-valid-gmail-address')
        },
        true
    );
});