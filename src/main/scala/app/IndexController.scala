package app

import service._

class IndexController extends IndexControllerBase 
  with RepositoryService with AccountService with SystemSettingsService with ActivityService

trait IndexControllerBase extends ControllerBase { self: RepositoryService 
  with SystemSettingsService with ActivityService =>
  
  get("/"){
    html.index(getRecentActivities(),
      getAccessibleRepositories(context.loginAccount, baseUrl),
      loadSystemSettings(),
      context.loginAccount.map{ account => getRepositoryNamesOfUser(account.userName) }.getOrElse(Nil))
  }

}