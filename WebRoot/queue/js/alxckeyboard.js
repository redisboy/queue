///<reference path="Scripts/jquery-1.4.1.min.js">
/* 
* alxckeyboard 1.0 
* Copyright (c) 2011 alxc  http://www.cnblogs.com/alxc/ * 
Date: 2011.06.01 
* jquery模拟键盘 
*/

(function ($) {
    $.extend($.fn, {
        alxckeyboard: function (setting) {
            var ps = $.extend({
            }, setting);

            return this.each(function () {
                var $object = $(this);

                var overlay = $('<div class="overlay"></div>').appendTo($('body'));
                overlay.css({
                    height: $(document).height()
                });

                var kb = $('<div class="keyboard">' + '</div>').appendTo($('body'));
                kb.append('<textarea style="overflow:hidden" class="txtwrite" rows="3" cols="52"></textarea>');
                kb.append('<ul class="area"></ul>');
//                for (var i = 0; i < 10; i++) {
                    kb.find('ul').append('<li class="clearleft letter">0</li>' + '<li class="letter">1</li>' + '<li class="letter">2</li>' + '<li class="letter">3</li>' + '<li class="letter">4</li>' + '<li class="letter">5</li>' + '<li class="letter">6</li>' + '<li class="letter">7</li>' + '<li class="letter">8</li>' + '<li class="letter">9</li>');
  //              }
                kb.find('ul').append('<li class="clearleft letter">q</li>' + '<li class="letter">w</li>' + '<li class="letter">e</li>' + '<li class="letter">r</li>' + '<li class="letter">t</li>' + '<li class="letter">y</li>' + '<li class="letter">u</li>' + '<li class="letter">i</li>' + '<li class="letter">o</li>' + '<li class="letter">p</li>' + '<li class="clearleft letter lettera">a</li>' + '<li class="letter">s</li>' + '<li class="letter">d</li>' + '<li class="letter">f</li>' + '<li class="letter">g</li>' + '<li class="letter">h</li>' + '<li class="letter">j</li>' + '<li class="letter">k</li>' + '<li class="letter">l</li>' + '<li class="clearleft capslk">大小写</li>' + '<li class="letter">z</li>' + '<li class="letter">x</li>' + '<li class="letter">c</li>' + '<li class="letter">v</li>' + '<li class="letter">b</li>' + '<li class="letter">n</li>' + '<li class="letter">m</li>' + '<li class="backspace">撤销</li>' + '<li class="clearleft back">后退</li>' + '<li class="go">前进</li>' + '<li class="space">&nbsp;</li>' + '<li class="ok">确定</li>' + '<li class="cancel">取消</li>');

  //              kb.css({ "left": (document.documentElement.clientWidth - kb.width()) / 2, "top": (document.documentElement.clientHeight - kb.height()) / 2 });

                var $txtwrite = $('.txtwrite'), //文本框
                e = $txtwrite[0], //文本框DOM对象
                caps = false; //大小写状态
                $txtwrite.focus();

                $(".keyboard li").click(function () {
                    var $this = $(this), //被点击按钮
                character = $this.html(), //被点击按钮内容
                html = $txtwrite.html(), //文本框内容
                pos = 0; //光标位置
                    if ($this.hasClass('ok'))//确定
                    {
                        if ($object.html())
                            $object.html(html);
                        else
                            $object.val(html);
                        kb.remove();
                        overlay.remove();
                    }
                    else if ($this.hasClass('cancel')) {
                        kb.remove();
                        overlay.remove();
                    }
                    else {
                        if (document.selection) {//IE获取位置
                            $txtwrite.focus();
                            var Sel = document.selection.createRange();
                            Sel.moveStart('character', -html.length);
                            pos = Sel.text.length;
                        }
                        else if (e.selectionStart || e.selectionStart == '0') {//FF获取位置
                            pos = e.selectionStart;
                        }

                        if ($this.hasClass('letter')) {//字母
                            if (caps) {
                                character = character.toUpperCase();
                            }
                            $txtwrite.html(html.substr(0, pos) + character + html.substr(pos, html.length - 1));
                            pos += 1;
                        }
                        else if ($this.hasClass('number')) {//数字
                            $txtwrite.html(html.substr(0, pos) + character + html.substr(pos, html.length - 1));
                            pos += 1;
                        }

                        else if ($this.hasClass('backspace'))//回格
                        {
                            $txtwrite.html(html.substr(0, pos - 1) + html.substr(pos, html.length - 1));
                            pos -= 1;
                        }
                        else if ($this.hasClass('capslk')) {//大小写切换
                            $('.letter').toggleClass('uppercase');
                            caps = caps == false ? true : false;
                        }
                        else if ($this.hasClass('space')) {//空格
                            $txtwrite.html(html.substr(0, pos) + ' ' + html.substr(pos, html.length - 1));
                            pos += 1;
                        }
                        else if ($this.hasClass('go')) {//前进1格
                            pos += 1;
                        }
                        else if ($this.hasClass('back')) {//回退1格
                            pos -= 1;
                        }
                        if (pos < 0)
                            pos = 0;
                        if (e.setSelectionRange) {//FF设置位置
                            e.focus();
                            e.setSelectionRange(pos, pos);
                        }
                        else if (e.createTextRange) {//IE获取位置
                            var range = e.createTextRange();
                            range.collapse(true);
                            range.moveStart('character', pos);
                            range.select();
                        }
                    }
                });
            });
        }
    });
})(jQuery);

