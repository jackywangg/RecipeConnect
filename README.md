# Recipe Connect Project

## Proposal
Recipe Connect is an application that primarily stores recipes, which includes its ingredients and instructions. However, a feature within this application is to call a random recipe within your collection of recipes to reduce time spent on choosing what to cook.

The focus group for this project are **students** or **homecooks** - those who primarily cook at home as opposed to eating outside or purchasing takeout. As a student who enjoys cooking but tries to minimize cooking time, I am interested in developing a project that can store and track recipes, making it easier to plan or choose meals.

## User Stories:
- As a user, I want to be able to add an ingredient into a recipe.          
- As a user, I want to be able to view the list of ingredients in a recipe. 
- As a user, I want to be able to view the number of ingredients in each recipe and the number of recipes in my collection.                                                   
- As a user, I want to be able to view the instructions pertaining to a recipe.
- As a user, I want to be able to remove a recipe form my list of recipes   
- As a user, I want to grab a random recipe among the recipes I've created.

- As a user, I want to be able to save the collection of my recipes (and its contents - i.e., ingredients and instructions) to a file.
- As a user, I want to be able to re-load my saved file and resume where I left off; otherwise, have the option to create a new file instance.

## **Instructions for Grader**
- You can generate the first required action related to the user story "removing an X from Y" by clicking on "View All Recipes" -> select a recipe then click on "Delete a Recipe".
- You can generate the second required action by picking a random X from Y through the "Random Recipe" button, assuming there are at least one recipe in the list.
- You can "add multiple Xs to a Y" by clicking on the "Add Recipe" button, which creates and adds a recipe to a list of recipes.
- You can save the state of my application by clicking on the "Save Recipes" button on the main page
- You can reload the state of my application by clicking on the "Load Recipes" button on the main page.
- You can locate two visual components by: 
    - (1) While having an empty recipe list and clicking on "View Recipes" -> "Random Recipe", to which you will see a sad image to the left of "No recipes available."; 
    - (2) Adding a recipe, then clicking on "View Recipes" -> Select a Recipe then click "View Recipe," to which you will see a cutlery image to the left.

## ** PHASE 4: Task 2**

Event Log:
Wed Aug 07 08:30:17 PDT 2024
Added ingredient: Curry Powder to recipe: Curry
Wed Aug 07 08:30:17 PDT 2024
Added ingredient: Potatoes to recipe: Curry
Wed Aug 07 08:30:17 PDT 2024
Added ingredient: Carrots to recipe: Curry
Wed Aug 07 08:30:17 PDT 2024
Added instruction to recipe: Curry
Wed Aug 07 08:30:17 PDT 2024
Added instruction to recipe: Curry
Wed Aug 07 08:30:36 PDT 2024
Added ingredient: Rice to recipe: Rice
Wed Aug 07 08:30:36 PDT 2024
Added ingredient: Water to recipe: Rice
Wed Aug 07 08:30:36 PDT 2024
Added instruction to recipe: Rice
Wed Aug 07 08:30:36 PDT 2024
Added instruction to recipe: Rice
Wed Aug 07 08:30:48 PDT 2024
Added ingredient: test to recipe: test
Wed Aug 07 08:30:48 PDT 2024
Added instruction to recipe: test
Wed Aug 07 08:30:52 PDT 2024
Selected random recipe: test
Wed Aug 07 08:30:57 PDT 2024
Deleted recipe: test
Wed Aug 07 08:30:59 PDT 2024
Saved recipes to ./data/recipes.json


## ** PHASE 4: Task 3**
If I had more time to work on this project, I would have merged RecipeIngredients and Ingredients to minimize clutter, or end up integrating Ingredients into RecipeIngredients. They were initially separate classes as I wanted to, but never did, implement a "cost" aspect of an ingredient, but it proved a bit too complex for me as the project went on. Furthermore, RecipeIngredients and Ingredients are not necessarily of different "types" insofar as RecipeIngredients contains Ingredients, providing me with another reason to integrate Ingredients into RecipeIngredients.

Another area of improvement is maybe to refactor the RecipeConnect class because it has tight coupling with other classes in the UI package. As such, any changes in RecipeConnect may affect the other, related classes. One idea is maybe to have RecipeConnect as a model that manages the application's back-end logic. Unrelatedly, maybe also centralizing Persistence classes into a PersistenceManager class that covers all persistence-related tasks might be an improvement. 

