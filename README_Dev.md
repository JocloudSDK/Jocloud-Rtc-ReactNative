# 开发
参考[文档](https://reactnative.cn/docs/getting-started.html)进行配置。

# 上传npm
### 外部npm
1. [官网](https://www.npmjs.com/)注册账号。

2. 使用以下命令账号登录。
```
npm login
```

3. 确保使用的是npm的原始镜像地址。如果更改，需要重新执行第二步。
```
查看当前镜像地址
npm config get registry

设置当前镜像成原始镜像地址
npm config set registry https://registry.npmjs.org/
```

4. 配置.gitignore，因为npm会根据此文件进行过滤。

5. 确保package.json中 **name** 是 **react-native-jocloud-rtc**。

6. 开始上传
```
npm publish
```

7. 撤销上传
```
npm unpublish --force
```

### YY内部npm
参考[文档](https://npm.yy.com/)
1. 登录
```
npm login --registry=https://npm-registry.yy.com --scope=@yy
```

2. 配置.gitignore，因为npm会根据此文件进行过滤。

3. 确保package.json中 **name** 是 **@yy/react-native-jocloud-rtc**。

4. 开始上传
```
npm publish --registry=https://npm-registry.yy.com
```