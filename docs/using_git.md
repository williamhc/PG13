Importing into Eclipse for the First Time
======================

1. First you need git. There are a number of ways of doing this but for now, the Github
client will do just fine.
 - [for mac](http://windows.github.com)
 - [for PC](http://mac.github.com)

2. Now, you need to get our repo. Head on over to [our Github page](http://github.com/williamhc/PG13)
and click "Clone in [OS]". This should prompt you to start up the Github client
and will then clone the repo. You now have a copy of our *code* on your computer.

3. Now lets go and create a project in Eclipse with this folder:
  1. File > New > Java Project > [Fill out stuff]
  2. Next > Link additional Source > [Find your cloned repo]
  3. Finish > Finish

4. That's it. You're done. You will probably have to get SWT for your platform now though.

Using Git
=================

The workflow that's widely used and accepted using Git is mostly branching with
pull requests. Here are the steps (assuming use of the Github client)

1. You get a task assigned to you on Teambox. Let's say, "Create some README's for git and Eclipse".

2. First, you sync your repository to the master branch of origin. You should be able to do this with the client easily (hit sync).
   - "Origin" is the remote version of our repo that we all "push" to and "pull" from.
   - "Branches" are exactly as they sound. They branch off from the trunk and add
   some functionality (or try to).
   - "Master" is the trunk. Master should always work because people need to be
   able to create branches from it to do their own work.

3. You do your stuff. Make your change, write some tests, make it purdy.

4. Now you're all ready to push it to everyone for them to use but since you're
working on a branch, we'll have to merge it in. You can do the merge yourself back
into master and then push your new master but that is generally not the best way
to work because nobody else saw your code. So we'll do *pull requests*! Push your
branch remotely and go to [github](http://github.com/williamhc/PG13). You should
see a newly pushed branch and be able to do a pull request. Fill out the form and
submit.

5. Someone will come along and review your changes and, if everything looks good,
merge it in! Note: we will be using travis-ci so we will be able to assure that
tests are passing on the branch before merging into master.

6. Profit.
