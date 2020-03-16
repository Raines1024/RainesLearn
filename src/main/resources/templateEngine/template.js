

//js实现模版引擎
var JTemplateEngine = function() {

    var encodeAsString = function(s) {
        return s.replace(/\\/g, '\\\\').replace(/'/g, '\\\'').replace(/\r/g, '\\r').replace(/\n/g, '\\n');
    }

    var compile = function(code) {
        console.log(code);
        // 输出缓冲
        var outBuf;
        // 仿php的echo函数，需要什么函数自己照着这个模式加就是了，项目中实际会有很多辅助函数
        var echo = function(s) {
            outBuf.push(s);
        }
        var __compiled;
        eval('__compiled = function(params) { ' + code + ' }');
        return function(params) {
            outBuf = new Array();
            __compiled(params);
            return outBuf.join('');
        }
    }

    var _class = function() { }

    // 解析模版字符串并编译
    _class.prototype.compile = function(template) {
        var codes = new Array();
        var buffer = new Array();
        var state = 0;
        for (i = 0; i <= template.length; i++) {
            var c;
            if (i == template.length) {
                c = '\0';
            } else {
                c = template.charAt(i);
            }
            switch (state) {
                case 0: // normal
                    if (c == '{') {
                        state = 1;
                    } else if (c == '\0') {
                        codes.push("echo('" + encodeAsString(buffer.join('')) + "');");
                    } else {
                        buffer.push(c);
                    }
                    break;
                case 1: // perhapse inside
                    if (c == '%') {
                        codes.push("echo('" + encodeAsString(buffer.join('')) + "');");
                        buffer = new Array();
                        state = 2;
                    } else if (c == '\0') {
                        buffer.push('{');
                        codes.push("echo('" + encodeAsString(buffer.join('')) + "');");
                    } else {
                        buffer.push('{');
                        buffer.push(c);
                        state = 0;
                    }
                    break;
                case 2: // inside
                    if (c == '%') {
                        state = 3;
                    } else if (c == '\0') {
                        codes.push(buffer.join(''));
                    } else {
                        buffer.push(c);
                    }
                    break;
                case 3: // perhapse outside
                    if (c == '}') {
                        codes.push(buffer.join(''));
                        buffer = new Array();
                        state = 0;
                    } else if (c == '\0') {
                        buffer.push('%');
                        codes.push(buffer.join(''));
                    } else {
                        buffer.push('%');
                        buffer.push(c);
                        state = 2;
                    }
                    break;
                default:
                    alert("bug");
            }
        }
        return compile(codes.join('\n'));
    }

    return _class;
}();