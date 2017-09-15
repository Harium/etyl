# Etyl [![Build Status](https://travis-ci.org/Harium/etyl.svg?branch=master)](https://travis-ci.org/Harium/etyl) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/4fe7372ce73741bf9955eb133e05e85b)](https://www.codacy.com/app/yuripourre/etyl?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Harium/etyl&amp;utm_campaign=Badge_Grade)

Etyl's core for Java Desktop

## Maven
```
<dependency>
    <groupId>com.harium.etyl</groupId>
    <artifactId>etyl</artifactId>
    <version>1.1.0</version>
</dependency>
```

## Generating a local jar
```
mvn package -Dmaven.test.skip=true
```

# Legacy
Etyl is a fork of Etyllica

## Migrating from Etyllica
```
cd src
find ./ -type f -exec sed -i 's/br.com.etyllica/com.harium.etyl/g' {} +
```


## License
You can choose between LGPL or Commercial license.

- [LGPL](http://www.gnu.org/licenses/lgpl.txt)
- [Commercial](http://www.harium.com/licenses/commercial.txt)




