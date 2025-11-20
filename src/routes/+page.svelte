<script lang="ts">
    // Logic later? 
  </script>
  
  <section class="hero">
    <div class="hero-text">
      <h1>Welcome to MediPoll</h1>
  
      <h2>Collect feedback that actually helps patients.</h2>
  
      <p>
        Quick, simple polls to understand how patients feel about their care.
      </p>
  
      <div class="hero-actions">
        <a class="btn btn-primary" href="/poll">Answer today’s poll</a>
        <a class="btn btn-outline" href="/admin">Go to admin dashboard</a>
      </div>
  
    </div>
  </section>
  
  <style>
    .hero {
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 4rem 1rem 3rem;
    }
  
    .hero-text {
      max-width: 600px;
      text-align: left;
    }
  
    h1 {
    margin: 0;
    font-size: clamp(3rem, 5vw + 1rem, 4.5rem); /* BIGGER */
    font-weight: 700;
    color: #111827;
    line-height: 1.1;
    }

  
    h2 {
    margin-top: 1rem;
    margin-bottom: 0.8rem;
    font-size: clamp(1.8rem, 2.5vw + 0.8rem, 2.3rem); /* BIGGER */
    font-weight: 600;
    color: #111827;
    }
  
    p {
    margin: 0;
    margin-top: 0.8rem;
    font-size: 1.15rem; /* bigger */
    color: #4b5563;
    line-height: 1.6;
    }
  
    .badge {
      display: inline-block;
      margin-top: 0.8rem;
      margin-bottom: 0.2rem;
      padding: 0.25rem 0.7rem;
      border-radius: 999px;
      font-size: 0.7rem;
      text-transform: uppercase;
      letter-spacing: 0.05em;
      background: #eff6ff;
      color: #1d4ed8;
      border: 1px solid #bfdbfe;
    }
  
    .hero-actions {
      display: flex;
      flex-wrap: wrap;
      gap: 0.75rem;
      margin-top: 1.4rem;
      margin-bottom: 0.8rem;
    }
  
    .hint {
      margin-top: 0.4rem;
      font-size: 0.85rem;
      color: #6b7280;
    }
  
    .btn {
      text-decoration: none;
      display: inline-flex;
      align-items: center;
      justify-content: center;
      padding: 0.9rem 2rem;
      border-radius: 999px;
      font-size: 1.1rem;
      font-weight: 500;
      border: 1px solid transparent;
      cursor: pointer;
      transition: background 0.15s ease, color 0.15s ease,
        box-shadow 0.15s ease, border-color 0.15s ease, transform 0.08s ease;
    }
  
    .btn-primary {
      background: #4CAF50;
      color: white;
      box-shadow: 0 10px 20px rgba(37, 99, 235, 0.25);
    }
  
    .btn-primary:hover {
      background: #4CAF50;
      transform: translateY(-1px);
      box-shadow: 0 14px 30px rgba(202, 235, 37, 0.4);
    }
  
    .btn-outline {
      background: #4CAF50;
      color: #ffffff;
      border-color:rgba;
    }
  
    .btn-outline:hover {
      background: #a2c4f1;
      border-color: #3f7ed6;
    }
  </style>