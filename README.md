# RedDot #

This is a REST API on Java using Spring MVC, Spring Security, 
Spring Data, Hibernate, PostgreSQL. For Controller description using Swagger UI.

## Functionality

Social news website where User can create account, public and private post article, subscribe to users,
find articles by tag and rate the articles of another users.
Administrator can ban or delete users, delete articles.

### Project Structure

#### Database

Have one postgresql database with articles and users tables. Database interaction 
takes place through [ArticleRepository](src/main/java/com/reddot/repositories/ArticleRepository.java) 
and [UserRepository](src/main/java/com/reddot/repositories/UserRepository.java). This class have @Repository
Spring Data annotation.

#### Business logic

In folder 'services' locate interfaces [IUserService](src/main/java/com/reddot/services/IUserService.java) 
and [IArticleService](src/main/java/com/reddot/services/IArticleService.java). Those interfaces are implemented through 
[UserService](src/main/java/com/reddot/services/UserService.java) and [ArticleService](src/main/java/com/reddot/services/ArticleService.java) classes.
All documentation about the work of this layout in interfaces.

#### Controllers

Description about [ArticleRestController](src/main/java/com/reddot/controllers/ArticleRestController.java),
 [UserRestController](src/main/java/com/reddot/controllers/UserRestController.java) and 
 [AuthRestController](src/main/java/com/reddot/controllers/AuthRestController.java) can find on [Swagger UI page](http://localhost:8888/swagger-ui.html)
 after execute application

