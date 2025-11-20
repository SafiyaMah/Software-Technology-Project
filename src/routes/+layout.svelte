<script lang="ts">
    export const ssr = false;

    import favicon from '$lib/assets/favicon.svg';
    import { onMount } from 'svelte';
    import { keycloak } from '$lib/keycloak';
    import { isAuthenticated, tokenParsed } from '$lib/authStore';

    let ready = false;

    onMount(async () => {
        try {
            const auth = await keycloak.init({
                onLoad: 'login-required',
                checkLoginIframe: false,
                pkceMethod: 'S256'
            });
            isAuthenticated.set(auth);
            tokenParsed.set(keycloak.tokenParsed ?? null);
        } finally {
            ready = true;
        }
    });

    function logout() {
        isAuthenticated.set(false);
        tokenParsed.set(null);
        keycloak.logout({
            redirectUri: window.location.origin // http://localhost:5173
        });
    }
</script>

<svelte:head>
    <link rel="icon" href="{favicon}" />
</svelte:head>

{#if !ready}
    <p>Loading authentication...</p>
{:else}
    <nav style="display:flex; gap:1rem; padding:1rem; border-bottom:1px solid #eee;">
        <a href="/">Home</a>
        <a href="/admin">Admin</a>
        <button on:click="{logout}">Logout</button>
    </nav>

    <slot />
{/if}