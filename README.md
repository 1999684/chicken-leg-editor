# 使用说明

# 本地部署

---

## 1.实用IDEA打开本项目

## 2.数据库相关

本项目使用的是MYSQL+Navicat Premium，实现数据库的可视化创建与管理

### 2.1 mysql安装

> 进入[MySQL ：： MySQL 社区下载](https://dev.mysql.com/downloads/)，选择 MySQL Installer for Windows ，建议使用第二个离线安装。
>
> - 不用登录，直接下载
>
> 下载并打开安装包，能看到版本，双击运行或者右键选择打开，打开后是一个安装向导，这个安装向导会先帮我们安装一个 mysql-installer 的程序，再通过该程序安装MySQL
>
> 如果之前已经在同一台计算机上安装过MySQL，安装程序可能会检测到现有的配置并自动跳过某些步骤。此外，安装程序可能会对系统进行环境检测，并根据检测结果自动配置一些设置。如果系统已经满足要求，安装程序可能会跳过相关的步骤。
>
> 需要注意的是在设置密码时候一定要设定自己记得住的密码，往后的过程中是必要的

> mysql环境变量配置
>
> - 在“我的电脑/此电脑”，点击右键属性，选择高级系统设置，在选择环境变量
> - 编辑系统变量的path变量
> - 添加mysql的安装路径中bin文件；如：`D:\MySQL\bin`
> - 确定按钮要一个一个按过去，绝对不要图省事就叉掉窗口
>
> window+R"快捷键，输入”cmd"进入window控制面板。
>
> 输入“mysql -uroot -p"敲击下回车，然后输入密码，再敲击回车，出现如下的内容代表配置成功。
>
> ```cmake
> PS C:\Users\myusername\Desktop> mysql -u root -p
> Enter password: **********
> Welcome to the MySQL monitor.  Commands end with ; or \g.
> Your MySQL connection id is 490
> Server version: 8.0.36 MySQL Community Server - GPL
> 
> Copyright (c) 2000, 2024, Oracle and/or its affiliates.
> 
> Oracle is a registered trademark of Oracle Corporation and/or its
> affiliates. Other names may be trademarks of their respective
> owners.
> 
> Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
> ```

### 2.2 Navicat Premium链接Mysql

> 连接mysql
>
> > 打开Navicat Premium后，点击左上角的连接图案，选择MySQL 
> >
> > 连接名可以自定义，但是最好为英语
> >
> > 主机名因为是本地部署，输入localhost，有时是默认的，之后输入自己的用户名、密码即可
> >
> > 点击测试连接，弹出弹窗，连接成功即可。点击确认

> 创建数据库
>
> > 首先双击数据库连接，打开连接
> >
> > 右击连接，新建数据库，依旧双击新建的数据库，打开数据库
> >
> > 右击数据库，选择运行SQL文件，文件点击`···`
> >
> > 选择代码文件中的sql文件：demo.sql，点击开始，完成后关闭即可

2.3 代码中配置

> - IDEA打开本项目
> - 根据路径`springboot\src\main\resources\application.yml`找到代码文件
>
> 如下内容：
>
> > ```yaml
> > server:
> >   port: 9090
> > spring:
> >   datasource:
> >     driver-class-name: com.mysql.jdbc.Driver
> >     url: jdbc:mysql://localhost:3306/你的数据库名称?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
> >     username: root
> >     password: 
> > ```
>
> - 在port后修改为你需要的端口号，密码、数据库名称不多说

完成

## 3.模型配置

### 3.1 代码位置

按照路径查找文件`vue\vue\src\views\Editor.vue`

在Editor.vue文件中找到第124行

```vue
const appId = ''; // 替换为你的百度翻译AppID
const apiKey = ''; // 替换为你的百度翻译APIKey
//模型续写
//应用接入处创建应用可以获取，本代码使用的是免费模型Ernie speed-128k
const AK = ""//替换为你的百度模型AK
const SK = ""//替换为你的百度模型SK
```

填入自己的相关ID与Key

### 3.2 如何获取

#### 3.2.1 百度翻译

打开网站：[百度翻译开放平台](https://api.fanyi.baidu.com/)

选择通用文本翻译，点击查看详情，点击立即使用，开启服务后，可以在开发者信息，看到AppId和APIKey。（记得注册应用）

#### 3.2.2 百度智能云控制台

##### 使用的模型

ERNIE-Speed-128K

ERNIE Speed是百度2024年最新发布的自研高性能大语言模型，通用能力优异，适合作为基座模型进行精调，更好地处理特定场景问题，同时具备极佳的推理性能。ERNIE-Speed-128K是模型的一个版本。

且目前免费使用中（免费的就是香啊）ヾ(≧▽≦*)o

##### 模型API key与Secret key 的获取

[百度智能云控制台](https://console.bce.baidu.com/qianfan/ais/console/applicationConsole/application)

打开百度智能云控制台，在应用接入处点击，并创建应用

在应用接入便可以看见AK和SK了

在代码中文件中找到并加入即可

# 系统使用

## 1.运行

![](https://github.com/1999684/picx-images-hosting/raw/master/课设/项目管理/1.9kg1jtvpl9.webp)

两个都要

如果打开这边没有。进行编辑配置

> sever属于npm
>
> > package.json(P):设置为vue\vue\package.json
> > 命令run，脚本serve
>
> springboot如下
>
> ![](https://github.com/1999684/picx-images-hosting/raw/master/课设/项目管理/2.54xmeknkmc.webp)

完成后运行，则可以在serve下的local或者network给的地址打开。

## 2.登陆界面：

![](https://github.com/1999684/picx-images-hosting/raw/master/课设/项目管理/1719010947523.3yeb5z1mdq.webp)

一开始没有用户信息，需要注册，自行注册后，填写账号密码后登录（验证码区分大小写）

## 3.页面首页：

![](https://github.com/1999684/picx-images-hosting/raw/master/课设/项目管理/1719011060584.231qdcrkhv.webp)

首页显示的会是你可以使用的编辑器（即你拥有权限的编辑器）编辑器id用于，多用户之间使用，例如需要两个用户编辑同一个编辑器文件时候，本无权限的用户可以在侧边栏的文本框内输入拥有那个编辑器的用户所提供的对应编辑器id。Enter后即可加入，会自动显示在首页表格中。

点击编辑即可参与编辑。

## 4.编辑器页面

页面简单，并不复杂

![](https://github.com/1999684/picx-images-hosting/raw/master/课设/项目管理/1719011372532.1aouvmho2n.webp)

在AI_help中

![](https://github.com/1999684/picx-images-hosting/raw/master/课设/项目管理/3.92pzv9co9n.webp)

可以使用翻译、续写、排版并适当扩写等ai功能，编辑器页面右下角的百度翻译结果部分，允许直接使用在编辑器内使用鼠标框选的方式，实现直接翻译，便于文章的阅读，响应速度较快。

除去这些基本功能外，在编辑器中还拥有以下功能：

视频嵌入：

![视频嵌入](https://github.com/1999684/picx-images-hosting/raw/master/课设/项目管理/视频嵌入.7w6omnqxnw.gif)

同步编辑：

![同步](https://github.com/1999684/picx-images-hosting/raw/master/课设/项目管理/同步.51e0gvbqs4.gif)

flash拓展：

![flash拓展](https://github.com/1999684/picx-images-hosting/raw/master/课设/项目管理/flash拓展.60u3u1ehuy.gif)

markdown格式输入：

![markdown-min](https://github.com/1999684/picx-images-hosting/raw/master/课设/项目管理/markdown-min.969lszwry9.gif)

本项目的海报设计：

![](https://github.com/1999684/picx-images-hosting/raw/master/课设/项目管理/海报？.1hs2r291as.webp)

# 后续开发可能

1.将模型appid与key作为前端可配置选项，做到无需改代码，打开即用，同时与每一个用户账户绑定

2.增加功能，适配格式，增加主题功能，迎合用户使用需求，保护用户视力

3.等等。。。。
