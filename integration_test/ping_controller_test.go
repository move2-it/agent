package test_controllers

import (
	"net/http"
	"net/http/httptest"
	"testing"

	"github.com/gin-gonic/gin"
	"github.com/stretchr/testify/assert"
	"move2.it/agent/controllers"
)

func SetUpRouter() *gin.Engine {
	router := gin.Default()
	router.GET("/ping", controllers.Ping)
	return router
}

func TestPingPongRequest(t *testing.T) {
	r := SetUpRouter()
	req, _ := http.NewRequest("GET", "/ping", nil)
	w := httptest.NewRecorder()
	r.ServeHTTP(w, req)

	res := string(w.Body.Bytes())

	assert.Equal(t, http.StatusOK, w.Code)
	assert.Equal(t, "pong", res)
}
