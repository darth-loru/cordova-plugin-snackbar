var snackbar = function(text, duration, button, textColor, actionColor, bgColor, successCallback){

    cordova.exec(
            successCallback, // success callback function
            null, // error callback function
            'MaterialSnackbar', // mapped to our native Java class called "CalendarPlugin"
            'materialSnackbar', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "text": text,
                "duration": duration,
                "button": button,
                "textColor": textColor,
                "actionColor": actionColor, 
                "bgColor": bgColor
            }]
        );
}
module.exports = snackbar;
