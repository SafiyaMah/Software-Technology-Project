<script lang="ts">
    import { isStaff, username } from "$lib/authStore";
</script>

<h1>Hello {$username}</h1>

{#if $isStaff}
    <p>Welcome to the admin dashboard.</p>
{:else}
    <p>You do not have access to this page.</p>
{/if}


