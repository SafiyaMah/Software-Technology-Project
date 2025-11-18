import KeyCloak from 'keycloak-js';

export const keycloak = new KeyCloak({
    url: 'http://localhost:8080',
    realm: 'test-sara',
    clientId: 'svelte-frontend'
});

