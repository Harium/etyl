# Etyl [![CircleCI](https://circleci.com/gh/Harium/etyl.svg?style=svg)](https://circleci.com/gh/Harium/etyl) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/4fe7372ce73741bf9955eb133e05e85b)](https://www.codacy.com/app/yuripourre/etyl?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Harium/etyl&amp;utm_campaign=Badge_Grade)

Etyl is the latest version of [Etyllica](https://github.com/etyllica/etyllica) (a 2D Game Engine made in Java). It can be used as a Game Engine or simply way to display something on the screen.

This project was originally built at the top of AWT but was modified to handle multiples backends (choose between AWT or LibGDX). 
You can run the exact same code on Desktop or Android (and possibly HTML5 and iOS in the future, thanks to LibGDX).

## How to start

- Clone or download the [minimal template example](https://github.com/Harium/etyl-template)
- Import to your favorite IDE
- Have fun

## Maven
```
<dependency>
    <groupId>com.harium.etyl</groupId>
    <artifactId>etyl</artifactId>
    <version>1.5.0</version>
</dependency>
```

## Local jar
Can't use maven? No problem, [build a local jar](https://github.com/Harium/etyl/wiki/Building-a-local-jar).

## Minimal Working Example
```
import com.harium.etyl.Etyl;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.commons.graphics.Graphics;

public class Main extends Etyl {

    public Main() {
        super(800, 600);
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setTitle("Etyl");
        app.init();
    }

    @Override
    public Application startApplication() {
        return new HelloWorld(w, h);
    }

    public class HelloWorld extends Application {
        public HelloWorld(int w, int h) {
            super(w, h);
        }

        @Override
        public void load() {}

        @Override
        public void draw(Graphics g) {
            g.setColor(Color.GREEN_ETYL);
            g.fillRect(0, 0, w, h);
        }
    }
}
```

## Related projects
- [Etyl GDX](https://github.com/Harium/etyl-gdx) - A GDX backend to etyl
- [Etyl Animation](https://github.com/Harium/etyl-animation) - Animation Module
- [Etyl UI](https://github.com/Harium/etyl-ui) - UI Module
- [Etyl Sound](https://github.com/Harium/etyl-sound) - Sound Module
- [Etyl i18n](https://github.com/Harium/etyl-i18n) - Internacionalization Module
- [Etyl Spriter](https://github.com/Harium/etyl-spriter) - Spriter Module
- [Etyl Ruby](https://github.com/Harium/etyl-ruby) - JRuby Module

## Contact
Do you have a request? Need some help?

[Open an issue](https://github.com/Harium/etyl/issues/new), lets talk.

Need some privacy?

Send me an e-mail: yuri@harium.com

## License
- [LGPL](http://www.gnu.org/licenses/lgpl.txt)

Basically you can use it freely (even in commercial projects) but if you
make some changes in Etyl, I would like to see it as a Pull Request (and
maybe add it to the project).
