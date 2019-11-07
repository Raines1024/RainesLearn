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
是因为本地分支和远程分支没有建立联系 (使用git branch -vv 可以查看本地分支和远程分支的关联关系) .根据命令行提示只需要执行以下命令即可
#git branch --set-upstream-to=origin/远程分支的名字 本地分支的名字 


git log --name-status 每次修改的文件列表, 显示状态

git branch --merged 查看哪些分支已被并入当前分支


### 删除分支
    查看项目的分支们(包括本地和远程)
    命令行 : $ git branch -a
    删除本地分支
    命令行 : $ git branch -d <BranchName>
    删除远程分支
    命令行 : $ git push origin --delete <BranchName>
    查看删除后分支们
    命令行 : $ git branch -a

git remote prune origin 清理远程分支，把本地不存在的远程分支删除