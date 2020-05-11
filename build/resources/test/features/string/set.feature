Feature: Set api for string
  Background:
  Scenario: calling set api
    When web client make api call to "POST":"/string/set/key/value" with request
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
    Then "/string/set/key/value" response with body
    """
    {
      "body": "set key: key with value: value successfully"
    }
    """

