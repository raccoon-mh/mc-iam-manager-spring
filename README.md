# MC-IAM-MANAGER-SPRING

test Springboot

```
mvn spring-boot:run 
mvn spring-boot:run -X
```


## SET KEYCLOAK

```JSON
{
  "realm": "TESTREALM",
  "enabled": true,
  "requiredCredentials": [
    "password"
  ],
  "users": [
    {
      "username": "alice",
      "enabled": true,
      "credentials": [
        {
          "type": "password",
          "value": "alice"
        }
      ],
      "realmRoles": [
        "user"
      ]
    },
    {
      "username": "jdoe",
      "enabled": true,
      "credentials": [
        {
          "type": "password",
          "value": "jdoe"
        }
      ],
      "realmRoles": [
        "user",
        "user_premium"
      ]
    },
    {
      "username": "service-account-authz-servlet",
      "enabled": true,
      "serviceAccountClientId": "authz-servlet",
      "clientRoles": {
        "authz-servlet" : ["uma_protection"]
      }
    }
  ],
  "roles": {
    "realm": [
      {
        "name": "user",
        "description": "User privileges"
      },
      {
        "name": "user_premium",
        "description": "User Premium privileges"
      }
    ]
  },
  "clients": [
    {
      "clientId": "authz-servlet",
      "enabled": true,
      "baseUrl": "http://localhost:8080/authz-servlet",
      "adminUrl": "http://localhost:8080/authz-servlet",
      "bearerOnly": false,
      "redirectUris": [
        "http://localhost:8080/authz-servlet/*",
        "http://127.0.0.1:8080/authz-servlet/*"
      ],
      "secret": "secret",
      "authorizationServicesEnabled": true,
      "directAccessGrantsEnabled": true,
      "authorizationSettings": {
        "resources": [
          {
            "name": "Protected Resource",
            "uri": "/*",
            "type": "http://servlet-authz/protected/resource",
            "scopes": [
              {
                "name": "urn:servlet-authz:protected:resource:access"
              }
            ]
          },
          {
            "name": "Premium Resource",
            "uri": "/protected/premium/*",
            "type": "urn:servlet-authz:protected:resource",
            "scopes": [
              {
                "name": "urn:servlet-authz:protected:premium:access"
              }
            ]
          }
        ],
        "policies": [
          {
            "name": "Any User Policy",
            "description": "Defines that any user can do something",
            "type": "role",
            "logic": "POSITIVE",
            "decisionStrategy": "UNANIMOUS",
            "config": {
              "roles": "[{\"id\":\"user\"}]"
            }
          },
          {
            "name": "Only Premium User Policy",
            "description": "Defines that only premium users can do something",
            "type": "role",
            "logic": "POSITIVE",
            "decisionStrategy": "UNANIMOUS",
            "config": {
              "roles": "[{\"id\":\"user_premium\"}]"
            }
          },
          {
            "name": "All Users Policy",
            "description": "Defines that all users can do something",
            "type": "aggregate",
            "logic": "POSITIVE",
            "decisionStrategy": "AFFIRMATIVE",
            "config": {
              "applyPolicies": "[\"Any User Policy\",\"Only Premium User Policy\"]"
            }
          },
          {
            "name": "Premium Resource Permission",
            "description": "A policy that defines access to premium resources",
            "type": "resource",
            "logic": "POSITIVE",
            "decisionStrategy": "UNANIMOUS",
            "config": {
              "resources": "[\"Premium Resource\"]",
              "applyPolicies": "[\"Only Premium User Policy\"]"
            }
          },
          {
            "name": "Protected Resource Permission",
            "description": "A policy that defines access to any protected resource",
            "type": "resource",
            "logic": "POSITIVE",
            "decisionStrategy": "UNANIMOUS",
            "config": {
              "resources": "[\"Protected Resource\"]",
              "applyPolicies": "[\"All Users Policy\"]"
            }
          }
        ],
        "scopes": [
          {
            "name": "urn:servlet-authz:protected:admin:access"
          },
          {
            "name": "urn:servlet-authz:protected:resource:access"
          },
          {
            "name": "urn:servlet-authz:protected:premium:access"
          },
          {
            "name": "urn:servlet-authz:page:main:actionForPremiumUser"
          },
          {
            "name": "urn:servlet-authz:page:main:actionForAdmin"
          },
          {
            "name": "urn:servlet-authz:page:main:actionForUser"
          }
        ]
      }
    }
  ]
}

```