# 贡献指南

[[English]](Contributing.md) | [简体中文]

## 构建

构建所有mc版本的mod:  
`./gradlew build`  
如果你只想构建单个mc版本的mod:  
`./gradlew {version}:build`  
例如: `./gradlew 1.20.6:build`

构建好的jar文件会在 'version\{version}\build\libs' 里
将所有构建的 jar 文件收集到项目根目录下的 'mod-jars' 目录:  
Windows (cmd/powershell): `cd docs` `.\collect_build.bat`  
Linux/Mac (console): `cd docs` `./collect_build.sh`

## Pull Request
欢迎为这个mod提交Pull Request！  
将拉取请求提交到`dev`分支，并按照[Conventional Commit]（https://www.conventionalcommits.org/）
