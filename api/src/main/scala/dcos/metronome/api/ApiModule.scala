package dcos.metronome.api

import controllers.Assets
import dcos.metronome.api.v1.controllers.{ ApplicationController, JobRunController, JobSpecController }
import dcos.metronome.jobrun.JobRunService
import dcos.metronome.jobspec.JobSpecService
import mesosphere.marathon.core.auth.AuthModule
import mesosphere.marathon.core.plugin.PluginManager
import play.api.http.HttpErrorHandler
import play.api.routing.Router
import mesosphere.marathon.plugin.auth.{ Authenticator, Authorizer }
import router.Routes

import com.softwaremill.macwire._

class ApiModule(
    jobSpecService:   JobSpecService,
    jobRunService:    JobRunService,
    pluginManager:    PluginManager,
    httpErrorHandler: HttpErrorHandler,
    assets:           Assets
) {

  lazy val applicationController = wire[ApplicationController]

  lazy val jobsSpecController = wire[JobSpecController]

  lazy val jobRunsController = wire[JobRunController]

  lazy val authModule: AuthModule = wire[AuthModule]

  lazy val authorizer: Authorizer = authModule.authorizer

  lazy val authenticator: Authenticator = authModule.authenticator

  lazy val router: Router = {
    // add the prefix string in local scope for the Routes constructor
    val prefix: String = "/"
    wire[Routes]
  }
}