@cleanup
Feature: Set api for string
  Background:
  Scenario: calling set api
    When web client make api call to "POST":"/string/set" with request
    """
    {
    "header": {
      "Content-Type": "text/plain;charset=UTF-8"
      },
    "body": {
      "key": "key",
      "value": "value"
      }
    }
    """
    Then "POST":"/string/set" response with body
    """
     {
      "body": "set key: key with value: value successfully"
     }
    """
    When web client make api call to "GET":"/string/get/key" with request
    """
    {
    "header": {
      "Content-Type": "text/plain;charset=UTF-8"
      },
    "params": {}
    }
    """
    Then "GET":"/string/get/key" response with body
    """
    {
      "body": "value"
    }
    """