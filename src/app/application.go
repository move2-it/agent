package app

import (
	"github.com/gin-gonic/gin"
)

var (
	router = gin.Default()
)

func StartApplication() {
	gin.SetMode(gin.ReleaseMode)
	mapUrls()
	router.Run(":8080")
}
