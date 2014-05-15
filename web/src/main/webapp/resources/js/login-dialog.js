function handleLoginRequest(xhr, status, args) {
    if (args.validationFailed || !args.loggedIn) {
        PF('loginDialogWidget').jq.effect("shake", {
            times : 5
        }, 100);
    } else {
        PF('loginDialogWidget').hide();
        $('#loginLink').fadeOut();
    }
}
