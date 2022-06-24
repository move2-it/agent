package app

import "move2.it/agent/controllers"

func mapUrls() {
	router.GET("/ping", controllers.Ping)
}
