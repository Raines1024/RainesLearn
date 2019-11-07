#创建分支
0.可以通过git branch -r 命令查看远端库的分支情况

1.从已有的分支创建新的分支(如从master分支),创建一个dev分支
git checkout -b dev

2.提交该分支到远程仓库
git push origin dev

新建本地分支后将本地分支推送到远程库, 使用git pull 或者 git push 的时候报错

      gitThere is no tracking information for the current branch.
      Please specify which branch you want to merge with.
      See git-pull(1) for details
       git pull \<remote\> \<branch\>
      If you wish to set tracking information for this branch you can do so with:
       git branch --set-upstream-to=origin/\<branch\> merged0.9.6
是因为本地分支和远程分支没有建立联系 (使用git branch -vv 可以查看本地分支和远程分支的关联关系) .根据命令行提示只需要执行以下命令即可<br>
git branch --set-upstream-to=origin/远程分支的名字 本地分支的名字 


git log --name-status 每次修改的文件列表, 显示状态

git branch --merged 查看哪些分支已被并入当前分支

<hr>

# 删除分支
    查看项目的分支们(包括本地和远程)
    命令行 : $ git branch -a
    删除本地分支
    命令行 : $ git branch -d <BranchName>
    删除远程分支
    命令行 : $ git push origin --delete <BranchName>
    查看删除后分支们
    命令行 : $ git branch -a

git remote prune origin 清理远程分支，把本地不存在的远程分支删除

<hr>

#合并远程2个分支
##查看分支
*  git branch -a
##切换分支
>比如同时有三个人开发，1.2最早是基于1.0，但是由于项目未发布，1.0,1.1,1.2全部都在同时开发，现在想把1.0已经增加的功能先合并到1.2；
dev beta
1.0 1.2
* 此时的步骤：check 1.2和1.0
<em style="color:#c7254e">*git checkout v1.0*
*git checkout v1.2*</em>

* 然后再v1.2的分支基础上执行merge
<em style="color:#c7254e">*git merge v1.0*</em>

如果没有报错，那就直接提交代码<em style="color:#c7254e">git push origin v1.2</em>
如果报错，基本是冲突了(比如)：
CONFLICT (content): Merge conflict in app/src/main/AndroidManifest.xml
Auto-merging app/build.gradle
CONFLICT (content): Merge conflict in app/build.gradle
Automatic merge failed; fix conflicts and then commit the result.

你需要去到提示的文件里把git自动标注的版本冲突注释掉，看你具体需要的功能进行删减

然后把冲突的文件git add，和commit，比如你有2个冲突文件，多文件add的时候直接空格隔开

git add app/src/main/AndroidManifest.xml app/build.gradle

最后再commit

git commit -m "解决2个分支之间的冲突"

###提交代码
<em style="color:#c7254e">git push origin v1.2</em>

###搞定

<hr>

##参考命令：
Git鼓励大量使用分支：
查看分支：git branch
创建分支：git branch \<name>
切换分支：git checkout \<name>
创建+切换分支：git checkout -b \<name>
合并某分支到当前分支：git merge \<name>
删除分支：git branch -d \<name>