<script lang="ts">
    import { isStaff, username } from '$lib/authStore';
</script>

{#if $isStaff}
    <section class="admin-page">
        <header>
            <h1>Admin dashboard</h1>
            <p class="subtitle">Welcome, {$username} !</p>
        </header>

        <div class="admin-card">
            <h2>Poll management</h2>
            <p>Here you can manage polls and view insights.</p>

            <div class="actions">
                <a class="btn btn-primary" href="/admin/polls/new">Create new poll</a>
                <a class="btn btn-outline" href="/admin">View existing polls</a>
            </div>
        </div>
    </section>
{:else}
    <section class="admin-page">
        <h1>Access denied</h1>
        <p class="subtitle">
            Hi {$username}! You do not have access to this page. Please contact an administrator if you
            think this is a mistake.
        </p>
    </section>
{/if}

<style>
    .admin-page {
        max-width: 960px;      
        margin: 2rem auto 3rem;
        padding: 0 1.5rem;   
        display: flex;
        flex-direction: column;
        gap: 1.25rem;
    }

    .admin-page h1 {
        margin: 0;
        font-size: 2rem;
        margin-bottom: 2.5rem;
    }

    .subtitle {
        text-align: center;
        margin: 1rem 0 2rem;
        font-size: 2.2rem;
        font-weight: 600;
        color: #1F2937; 
        line-height: 1.2;
        margin-top: 3.5rem;
    }

    .admin-card {
        background: #ffffff;
        border-radius: 20px; 
        padding: 40px;      
        box-shadow: 0 25px 50px rgba(15, 23, 42, 0.15);
        border: 1px solid rgba(148, 163, 184, 0.3);
        max-width: 900px;    
        margin: 2rem auto;   
    }

    .admin-card p {
        margin-top: 10px;
        font-size: 1.1rem;  
        color: #1F2937;
    }   

    .actions {
        margin-top: 24px;
        display: flex;
        gap: 1.2rem; 
    }

    .btn {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        padding: 0.9rem 2rem;  
        font-size: 1.05rem;   
        border-radius: 40px;   
        font-weight: 600;
        text-decoration: none; 
    }

    .btn-primary {
        background: #2f9e44;     
        color: #ffffff;
        box-shadow: 0 10px 20px rgba(47, 158, 68, 0.25);
    }

    .btn-primary:hover {
        background: #27853a;
        transform: translateY(-1px);
    }

    .btn-outline {
        background: #f3faf5;
        color: #2f9e44;
        border-color: #b7e2c0;
    }

    .btn-outline:hover {
        background: #eff6ff;
    }

    
</style>