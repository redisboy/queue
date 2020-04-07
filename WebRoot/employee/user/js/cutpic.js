var popUpWin = 0;
        function PopUpWindow(URLStr, left, top, width, height, newWin, scrollbars) {
            if (typeof (newWin) == "undefined")
                newWin = false;

            if (typeof (left) == "undefined")
                left = 100;

            if (typeof (top) == "undefined")
                top = 0;

            if (typeof (width) == "undefined")
                width = 800;

            if (typeof (height) == "undefined")
                height = 760;

            if (newWin) {
                open(URLStr, '', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=' + scrollbars + ',resizable=yes,copyhistory=yes,width=' + width + ',height=' + height + ',left=' + left + ', top=' + top + ',screenX=' + left + ',screenY=' + top + '');
                return;
            }

            if (typeof (scrollbars) == "undefined") {
                scrollbars = 0;
            }

            if (popUpWin) {
                if (!popUpWin.closed) popUpWin.close();
            }
            popUpWin = open(URLStr, 'popUpWin', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=' + scrollbars + ',resizable=yes,copyhistory=yes,width=' + width + ',height=' + height + ',left=' + left + ', top=' + top + ',screenX=' + left + ',screenY=' + top + '');
            popUpWin.focus();
        }