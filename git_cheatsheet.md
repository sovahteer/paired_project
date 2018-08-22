# Git Cheat Sheet

## Creating a new branch

```bash
git checkout prev_branch # e.g. master, develop, etc.
git checkout -b new_branch # will contain all the history from prev_branch
```

## Pushing a new branch to GitHub

```bash
git push -u origin branch_name
```

`-u` tells git to always push to `branch_name` on GitHub from the current branch in git. After running this command once you can simply do:

```bash
git push
```

## Merging a feature branch into develop

```bash
git checkout develop
git pull # make sure develop is up-to-date with remote

git checkout feature/my_feature # move back to feature branch
git merge develop # merge develop into feature branch, so we can fix any conflicts there
```

At this point one of three things will happen:
- it will "fast-forward" you commits on top of develop
- it will merge using the "recursive strategy" (i.e. make a new merge commit)
- it will conflict and force you to fix the changes, then commit the fixes

Once you've dealt with this:

```bash
git checkout develop # move back to develop
git pull # something might have changed while we were merging develop into our feature if so repeat that
git merge feature/my_feature # merge feature into develop
```

This last step shouldn't ever conflict or use the recursive strategy since we already made the fixes on our feature branch.

## Merging develop into master

Identical to above, with `feature/my_feature` replaced by `develop` and `develop` replaced by `master`.
