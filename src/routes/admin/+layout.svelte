<script>
    import { isStaff } from '$lib/authStore';
</script>

{#if $isStaff}
    <!-- Staff are allowed to see all admin pages -->
    <slot />
{:else}
    <p>Access denied. You do not have permission to view this page.</p>
{/if}