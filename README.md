> Table of Content
> 1. ##### Default Rules 
> 2. Basic Operation
> 3. Problem-Solution (Keep updating)

---
### 1. Default Rules
- Use Remote Branch is enough for this project.
- Before you touch on other people branch, please inform the owner.
- When you are not sure there's  a logic bug inside your code, don't merge the branch into "master". Kindy drop the message into group and request other to debug.
- Pull all the Object properties become private/default instance variable.
- Ensure every instance variable has its accessor and mutator.
- It's better to add slight comment into each paragraph of your code (i.e: just add one comment in front of a for loop to tell other the function)

### 2.Basic Operation
#### 2.1 Before you start
-  "Git"  - > "Remote" -> Select your own branch
- Before start to code, ensure that have no blue arrow beside "master" branch. This symbol means branch is outdated!
  
![Screenshot 2024-04-26 010151](https://github.com/johnnytan55/DS-Assignment-G2/assets/147262649/240e978e-4592-4f12-ad41-16b136eb311c)


- Click into the outdated branch and open "Terminal".
- Enter command "git pull origin master"

#### 2.2 After you done
- When you are finished a part of code that brings some new function, always commit and pull on to your branch
- Click your branch, select "commit" 
- Select the changed file, add more comment on that.
- Click "commit and push"
  
![Screenshot 2024-04-26 013158](https://github.com/johnnytan55/DS-Assignment-G2/assets/147262649/46a45e7e-98bd-45fb-87fd-d566146947da)

- Now, your code has been uploaded on the Github. 
- For now, if you cannot ensure the code is no bug. Don't pull request. 
- The successful request will merge your code into "master". Don't poison other people code.

#### 2.3 Check someone code
-  Select the branch you want.
- "Terminal " -> "git pull origin <branch_name>" 
