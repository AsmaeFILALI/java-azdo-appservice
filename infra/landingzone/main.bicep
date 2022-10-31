param webapps array
param resourceGroupName string
param location string

targetScope = 'subscription'

resource rgWebApps 'Microsoft.Resources/resourceGroups@2021-04-01' = {
  name: resourceGroupName
  location: location
}

module appService '../modules/appservice.bicep'= [for webapp in webapps: {
  scope: rgWebApps
  name: 'deploy-${webapp.Name}'
  params:{
    sku: webapp.sku
    webAppName: webapp.name
    location: location
    appServicePlanName: webapp.appServicePlanName
  }
  
}]
